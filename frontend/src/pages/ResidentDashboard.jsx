import React, { useState, useEffect } from 'react';
import Topbar from '../components/Topbar';
import { Home, Bell, Wrench, IndianRupee, HelpCircle, CheckCircle, Save } from 'lucide-react';
import { api } from '../services/api';

const ResidentDashboard = () => {
  const [showProfile, setShowProfile] = useState(false);
  const [loading, setLoading] = useState(true);
  
  // States
  const [flats, setFlats] = useState([]);
  const [myResidentParams, setMyResidentParams] = useState(null); // Simulated logged-in user context
  
  const [issues, setIssues] = useState([]);
  const [notices, setNotices] = useState([]);
  const [maintenance, setMaintenance] = useState([]);
  
  // Profile Form State
  const [profileForm, setProfileForm] = useState({ id: '', name: '', email: '', phone: '', flatId: '' });
  
  // Issue submitting
  const [issueText, setIssueText] = useState('');
  const [suggestion, setSuggestion] = useState(null);

  const fetchAppData = async () => {
    setLoading(true);
    try {
      const [f, r, n, i, m] = await Promise.all([
        api.getFlats(), api.getResidents(), api.getNotices(), api.getIssues(), api.getAllMaintenance()
      ]);
      setFlats(f);
      setNotices(n);
      
      // MOCK LOGIN: Just take the first resident or create an empty context if no residents exist yet.
      const currentUser = r.length > 0 ? r[0] : null;
      setMyResidentParams(currentUser);
      
      if(currentUser) {
        setProfileForm({
          id: currentUser.id,
          name: currentUser.name || '',
          email: currentUser.email || '',
          phone: currentUser.phone || '',
          flatId: currentUser.flat?.id || ''
        });
        
        // Filter issues & maintenance locally for demonstration matching this user's flat
        setIssues(i.filter(iss => iss.resident?.id === currentUser.id));
        if(currentUser.flat) {
          setMaintenance(m.filter(main => main.flat?.id === currentUser.flat.id));
        }
      }
      
    } catch (e) {
      console.error(e);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => { fetchAppData(); }, []);

  const handleProfileSave = async (e) => {
    e.preventDefault();
    if (!profileForm.flatId) {
       alert("Please select a flat"); 
       return;
    }
    const payload = { 
       id: profileForm.id ? profileForm.id : null,
       name: profileForm.name, 
       email: profileForm.email, 
       phone: profileForm.phone, 
       flat: { id: profileForm.flatId } 
    };
    await api.saveResident(payload);
    alert("Profile saved successfully to Database! Admin can now see your details.");
    fetchAppData(); // Refresh
  };

  const handleIssueSubmit = async (e) => {
    e.preventDefault();
    if (!issueText.trim() || !myResidentParams) return;

    let category = 'General';
    let suggestionText = 'Please contact the support desk.';
    const textLow = issueText.toLowerCase();
    if (textLow.includes('water') || textLow.includes('tap') || textLow.includes('leak') || textLow.includes('bathroom') || textLow.includes('plumb')) {
      category = 'Plumbing';
      suggestionText = 'Contact Plumber at 9876543211';
    } else if (textLow.includes('electric') || textLow.includes('light') || textLow.includes('wire')) {
      category = 'Electrical';
      suggestionText = 'Contact Electrician at 9876543212';
    }

    setSuggestion({ category, text: suggestionText });
    
    // Call Backend
    const payload = { description: issueText, category, status: 'Pending' };
    await api.addIssue(myResidentParams.id, payload);
    
    setIssueText('');
    fetchAppData(); // Immediately reflect new issue
  };

  // Allow residents to mark their own issue resolved
  const markResolved = async (id) => {
    await api.updateIssueStatus(id, 'Resolved');
    fetchAppData();
  };

  if (loading) return <div className="p-8 text-center">Loading context...</div>;

  return (
    <div>
      <Topbar onProfileClick={() => setShowProfile(!showProfile)} />
      
      <div className="container">
      
        {!myResidentParams && !showProfile && (
          <div className="card mb-4" style={{border: '1px solid var(--danger)'}}>
             <h3 className="text-xl font-bold text-danger mb-2">No Profile Found!</h3>
             <p>Please open the Profile icon in the Topbar and create/assign your flat. You won't be able to log issues otherwise.</p>
          </div>
        )}

        {showProfile && (
          <div className="card full-width mb-6" style={{ animation: 'fadeIn 0.2s ease', border: '2px solid var(--primary)' }}>
            <h2 className="text-xl mb-4 font-semibold">User Profile Setup</h2>
            <form onSubmit={handleProfileSave}>
              <div className="grid" style={{ display: 'grid', gridTemplateColumns: 'minmax(200px, 1fr) minmax(200px, 1fr)', gap: '16px' }}>
                <div>
                  <label className="text-muted">Name</label>
                  <input required className="input mt-2" value={profileForm.name} onChange={e=>setProfileForm({...profileForm, name: e.target.value})} />
                </div>
                <div>
                  <label className="text-muted">Email</label>
                  <input required type="email" className="input mt-2" value={profileForm.email} onChange={e=>setProfileForm({...profileForm, email: e.target.value})} />
                </div>
                <div>
                  <label className="text-muted">Phone</label>
                  <input required className="input mt-2" value={profileForm.phone} onChange={e=>setProfileForm({...profileForm, phone: e.target.value})} />
                </div>
                <div>
                  <label className="text-muted">Assign Flat (Admin must establish first)</label>
                  <select required className="input mt-2" value={profileForm.flatId} onChange={e=>setProfileForm({...profileForm, flatId: e.target.value})}>
                    <option value="">Select your flat...</option>
                    {flats.map(f => (
                      <option key={f.id} value={f.id}>{f.flatNumber} (Blk: {f.block})</option>
                    ))}
                  </select>
                </div>
              </div>
              <button type="submit" className="btn mt-6"><Save size={16} /> Save Profile to Database</button>
            </form>
          </div>
        )}

        <div className="dashboard-grid">
          {/* Mapped Flat Details */}
          <div className="card">
            <h2 className="text-xl mb-4 font-semibold flex items-center gap-2 text-primary"><Home size={20} /> My Flat Details</h2>
            {!myResidentParams?.flat ? <p className="text-muted italic">No data available. Set your profile.</p> : (
              <>
                <div className="mt-4 flex flex-col gap-2 border-b pb-4 mb-4" style={{ borderColor: 'var(--border)' }}>
                  <div className="flex justify-between"><span className="text-muted">Flat No:</span> <b>{myResidentParams.flat.flatNumber}</b></div>
                  <div className="flex justify-between"><span className="text-muted">Block:</span> <b>{myResidentParams.flat.block}</b></div>
                </div>
                <div className="flex flex-col gap-2">
                  <h3 className="font-semibold text-lg">My Details</h3>
                  <div className="flex justify-between"><span className="text-muted">Name:</span> <b>{myResidentParams.name}</b></div>
                  <div className="flex justify-between"><span className="text-muted">Email:</span> <b>{myResidentParams.email}</b></div>
                </div>
              </>
            )}
          </div>

          {/* Connected Notices */}
          <div className="card" style={{ borderLeft: '4px solid var(--warning)' }}>
            <h2 className="text-xl mb-4 font-semibold flex items-center gap-2 text-warning"><Bell size={20} /> Notices</h2>
            {notices.length === 0 ? <p className="text-muted italic">No notices posted</p> : (
              <div className="flex flex-col gap-3">
                {notices.map(n => (
                  <div key={n.id} className="p-4 rounded-xl" style={{ border: '1px solid var(--border)', background: '#FEF3C7' }}>
                    <h3 className="font-bold mb-1">{n.title}</h3>
                    <p className="text-sm">{n.message}</p>
                  </div>
                ))}
              </div>
            )}
          </div>

          {/* Unified Issue Module */}
          <div className="card full-width">
            <h2 className="text-xl mb-4 font-semibold flex items-center gap-2 text-danger"><Wrench size={20} /> Raise an Issue</h2>
            {myResidentParams ? (
              <form onSubmit={handleIssueSubmit} className="flex gap-4">
                <textarea required className="input flex-1" placeholder="Describe your problem... (e.g. Broken wall)" value={issueText} onChange={(e) => setIssueText(e.target.value)} style={{ minHeight: '50px' }} />
                <button className="btn" type="submit" disabled={!issueText}>Submit Database Complaint</button>
              </form>
            ) : <p className="text-muted italic">Complete your profile to submit issues.</p>}

            {suggestion && (
              <div className="mt-4 p-4 rounded-xl" style={{ background: '#EFF6FF', border: '1px solid #BFDBFE' }}>
                <h3 className="font-semibold text-primary flex items-center gap-2 mb-2"><HelpCircle size={16} /> Smart Category Result</h3>
                <div className="grid" style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '8px' }}>
                  <div><span className="text-muted">Assigned NLP Category:</span> <b>{suggestion.category}</b></div>
                  <div><span className="text-muted">Solution:</span> <b>{suggestion.text}</b></div>
                </div>
              </div>
            )}

            <h3 className="font-semibold mt-8 mb-4">📋 My Issues History</h3>
            {issues.length === 0 ? <p className="text-muted italic">No issues raised yet</p> : (
              <div className="table-wrapper">
                <table>
                  <thead><tr><th>Description</th><th>Category</th><th>Status</th><th>Action</th></tr></thead>
                  <tbody>
                    {issues.map(iss => (
                      <tr key={iss.id}>
                        <td>{iss.description}</td>
                        <td><span className="badge badge-primary">{iss.category}</span></td>
                        <td><span className={`badge badge-${iss.status === 'Resolved' ? 'success' : 'warning'}`}>{iss.status}</span></td>
                        <td>
                          {iss.status === 'Pending' && (
                            <button className="btn btn-outline" style={{ padding: '4px 8px', fontSize: '12px' }} onClick={() => markResolved(iss.id)}>Mark Resolved</button>
                          )}
                          {iss.status === 'Resolved' && <span className="text-success"><CheckCircle size={16} /></span>}
                        </td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              </div>
            )}
          </div>
          
          {/* Remote Maintenance */}
          <div className="card full-width">
            <h2 className="text-xl mb-4 font-semibold flex items-center gap-2 text-success"><IndianRupee size={20} /> My Maintenance</h2>
            {maintenance.length === 0 ? <p className="text-muted italic">No maintenance data available for your flat.</p> : (
              <div className="table-wrapper">
                <table>
                  <thead><tr><th>Amount</th><th>Status</th><th>Action</th></tr></thead>
                  <tbody>
                    {maintenance.map(m => (
                      <tr key={m.id}>
                        <td>{m.amount}</td>
                        <td><span className={`badge badge-${m.status === 'Paid' ? 'success' : 'warning'}`}>{m.status}</span></td>
                        <td>
                          {m.status === 'Pending' ? (
                             <button className="btn btn-success" style={{ padding: '6px 12px' }} onClick={async () => { await api.payMaintenance(m.id); fetchAppData(); }}>Pay Now</button>
                          ) : <span className="text-success ml-2"><CheckCircle size={16} /></span>}
                        </td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              </div>
            )}
          </div>
        </div>
      </div>
    </div>
  );
};

export default ResidentDashboard;
