import React, { useState, useEffect } from 'react';
import Topbar from '../components/Topbar';
import { Users, Home, Bell, Wrench, IndianRupee, Plus, Check, Trash2, Edit } from 'lucide-react';
import { api } from '../services/api';

const AdminDashboard = () => {
  const [activeTab, setActiveTab] = useState('Flats');
  
  const [flats, setFlats] = useState([]);
  const [adminFlats, setAdminFlats] = useState([]);
  const [residents, setResidents] = useState([]);
  const [issues, setIssues] = useState([]);
  const [notices, setNotices] = useState([]);
  const [maintenance, setMaintenance] = useState([]);

  const [loading, setLoading] = useState(true);

  // Forms
  const [flatForm, setFlatForm] = useState({ flatNumber: '', block: '', type: '2BHK', ownerName: '', phoneNumber: '' });
  const [residentForm, setResidentForm] = useState({ name: '', email: '', phone: '', flatId: '' });
  const [noticeForm, setNoticeForm] = useState({ title: '', message: '' });

  const fetchData = async () => {
    setLoading(true);
    try {
      const [f, af, r, i, n, m] = await Promise.all([
        api.getFlats(),
        api.getFlatsAdmin(),
        api.getResidents(),
        api.getIssues(),
        api.getNotices(),
        api.getAllMaintenance()
      ]);
      setFlats(f);
      setAdminFlats(af);
      setResidents(r);
      setIssues(i);
      setNotices(n);
      setMaintenance(m);
    } catch (e) {
      console.error(e);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => { fetchData(); }, []);

  // --- Handlers ---
  const handleAddFlat = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch('http://localhost:8082/flats', { 
        method: 'POST', 
        headers: {'Content-Type': 'application/json'}, 
        body: JSON.stringify(flatForm) 
      });
      if (!response.ok) {
         alert("Failed to add flat. Are you sure that Flat Number is uniquely new?");
         return;
      }
      setFlatForm({ flatNumber: '', block: '', type: '2BHK', ownerName: '', phoneNumber: '' });
      fetchData();
      alert("Flat Added Successfully!");
    } catch (err) {
      alert("Error reaching server: " + err.message);
    }
  };

  const handleDeleteFlat = async (id) => {
    await api.deleteFlat(id);
    fetchData();
  };

  const handleAddResident = async (e) => {
    e.preventDefault();
    const payload = { ...residentForm, flat: { id: residentForm.flatId } };
    await api.saveResident(payload);
    setResidentForm({ name: '', email: '', phone: '', flatId: '' });
    fetchData();
  };

  const handleDeleteResident = async (id) => {
    await api.deleteResident(id);
    fetchData();
  };

  const handleResolveIssue = async (id) => {
    await api.updateIssueStatus(id, 'Resolved');
    fetchData();
  };

  const handlePostNotice = async (e) => {
    e.preventDefault();
    await api.addNotice(noticeForm);
    setNoticeForm({ title: '', message: '' });
    fetchData();
  };

  const handleMarkPaid = async (id) => {
    await api.payMaintenance(id);
    fetchData();
  };

  if (loading) return <div className="p-8 text-center">Loading...</div>;

  return (
    <div>
      <Topbar onProfileClick={() => {}} />
      
      <div className="container">
        <div className="flex justify-between items-center mb-6">
          <h2 className="text-2xl font-bold">Admin Panel</h2>
          <div className="stats-grid">
            <div className="stat-box">
              <div className="value">{flats.length}</div>
              <div className="text-muted font-semibold">Total Flats</div>
            </div>
            <div className="stat-box">
              <div className="value">{residents.length}</div>
              <div className="text-muted font-semibold">Residents</div>
            </div>
          </div>
        </div>

        <div className="flex gap-4 mb-6" style={{ overflowX: 'auto', paddingBottom: '10px' }}>
          {['Flats', 'Residents', 'Issues', 'Notices', 'Maintenance'].map(tab => (
            <button 
              key={tab}
              className={`btn ${activeTab === tab ? '' : 'btn-outline'}`}
              onClick={() => setActiveTab(tab)}
              style={{ minWidth: '120px' }}
            >
              {tab === 'Flats' && <Home size={16} />}
              {tab === 'Residents' && <Users size={16} />}
              {tab === 'Issues' && <Wrench size={16} />}
              {tab === 'Notices' && <Bell size={16} />}
              {tab === 'Maintenance' && <IndianRupee size={16} />}
              {tab}
            </button>
          ))}
        </div>

        <div className="card" style={{ animation: 'fadeIn 0.2s ease' }}>
          {/* FLATS TAB */}
          {activeTab === 'Flats' && (
            <div>
              <div className="flex justify-between mb-4">
                <h3 className="text-xl font-bold"><Home className="inline" size={20}/> Manage Flats</h3>
              </div>
              <form className="flex gap-2 mb-6" onSubmit={handleAddFlat} style={{ flexWrap: 'wrap' }}>
                <input required className="input" style={{flex: 1, minWidth: '150px'}} placeholder="Flat No (unique)" value={flatForm.flatNumber} onChange={e=>setFlatForm({...flatForm, flatNumber: e.target.value})} />
                <input required className="input" style={{flex: 1, minWidth: '100px'}} placeholder="Block" value={flatForm.block} onChange={e=>setFlatForm({...flatForm, block: e.target.value})} />
                <input required className="input" style={{flex: 1, minWidth: '150px'}} placeholder="Owner" value={flatForm.ownerName} onChange={e=>setFlatForm({...flatForm, ownerName: e.target.value})} />
                <input required className="input" style={{flex: 1, minWidth: '150px'}} placeholder="Phone" value={flatForm.phoneNumber} onChange={e=>setFlatForm({...flatForm, phoneNumber: e.target.value})} />
                <button type="submit" className="btn"><Plus size={16}/> Add Flat</button>
              </form>

              {flats.length === 0 ? <p className="text-muted italic">No data available</p> : (
                <div className="table-wrapper">
                  <table>
                    <thead><tr><th>Flat No</th><th>Block</th><th>Owner</th><th>Action</th></tr></thead>
                    <tbody>
                      {flats.map(f => (
                        <tr key={f.id}>
                          <td>{f.flatNumber}</td><td>{f.block}</td><td>{f.ownerName}</td>
                          <td>
                            <button className="btn btn-danger btn-outline" style={{ padding: '6px' }} onClick={() => handleDeleteFlat(f.id)}><Trash2 size={14}/></button>
                          </td>
                        </tr>
                      ))}
                    </tbody>
                  </table>
                </div>
              )}
            </div>
          )}

          {/* RESIDENTS TAB */}
          {activeTab === 'Residents' && (
            <div>
              <div className="flex justify-between mb-4">
                <h3 className="text-xl font-bold"><Users className="inline" size={20}/> Manage Residents</h3>
              </div>
              
              <form className="flex gap-2 mb-6" onSubmit={handleAddResident}>
                <select required className="input" value={residentForm.flatId} onChange={e=>setResidentForm({...residentForm, flatId: e.target.value})}>
                  <option value="">Select Flat...</option>
                  {flats.map(f => <option key={f.id} value={f.id}>{f.flatNumber} ({f.block})</option>)}
                </select>
                <input required className="input" placeholder="Name" value={residentForm.name} onChange={e=>setResidentForm({...residentForm, name: e.target.value})} />
                <input required className="input" placeholder="Email" type="email" value={residentForm.email} onChange={e=>setResidentForm({...residentForm, email: e.target.value})} />
                <button type="submit" className="btn"><Plus size={16}/> Add</button>
              </form>

              {residents.length === 0 ? <p className="text-muted italic">No data available</p> : (
                <div className="table-wrapper">
                  <table>
                    <thead><tr><th>Name</th><th>Flat</th><th>Email</th><th>Action</th></tr></thead>
                    <tbody>
                      {residents.map(r => (
                        <tr key={r.id}>
                          <td>{r.name}</td><td>{r.flat?.flatNumber || '-'}</td><td>{r.email}</td>
                          <td>
                            <button className="btn btn-danger btn-outline" style={{ padding: '6px' }} onClick={() => handleDeleteResident(r.id)}><Trash2 size={14}/></button>
                          </td>
                        </tr>
                      ))}
                    </tbody>
                  </table>
                </div>
              )}
            </div>
          )}

          {/* ISSUES TAB */}
          {activeTab === 'Issues' && (
            <div>
              <h3 className="text-xl font-bold mb-4"><Wrench className="inline" size={20}/> All Issues</h3>
              {issues.length === 0 ? <p className="text-muted italic">No issues raised yet</p> : (
                <div className="table-wrapper">
                  <table>
                    <thead>
                      <tr><th>Flat No</th><th>Description</th><th>Category</th><th>Status</th></tr>
                    </thead>
                    <tbody>
                      {issues.map(i => (
                        <tr key={i.id}>
                          <td>{i.resident?.flat?.flatNumber || '-'}</td>
                          <td>{i.description}</td>
                          <td><span className="badge badge-primary">{i.category}</span></td>
                          <td><span className={`badge badge-${i.status === 'Resolved' ? 'success' : 'warning'}`}>{i.status}</span></td>
                        </tr>
                      ))}
                    </tbody>
                  </table>
                </div>
              )}
            </div>
          )}

          {/* NOTICES TAB */}
          {activeTab === 'Notices' && (
            <div>
              <h3 className="text-xl font-bold mb-4"><Bell className="inline" size={20}/> Post Notice</h3>
              <form className="flex flex-col gap-4 max-w-lg mb-8" onSubmit={handlePostNotice}>
                <input required className="input" placeholder="Notice Title" value={noticeForm.title} onChange={e=>setNoticeForm({...noticeForm, title: e.target.value})} />
                <textarea required className="input" placeholder="Notice Message" value={noticeForm.message} onChange={e=>setNoticeForm({...noticeForm, message: e.target.value})} />
                <button type="submit" className="btn"><Bell size={16} /> Post Notice</button>
              </form>
              
              <h4 className="font-bold mb-2">Previous Notices</h4>
              {notices.length === 0 ? <p className="text-muted italic">No notices posted</p> : (
                <ul className="pl-4">
                  {notices.map(n => <li key={n.id}>{n.title} - {n.message}</li>)}
                </ul>
              )}
            </div>
          )}

          {/* MAINTENANCE TAB */}
          {activeTab === 'Maintenance' && (
            <div>
              <h3 className="text-xl font-bold mb-4"><IndianRupee className="inline" size={20}/> Maintenance Payments</h3>
              {maintenance.length === 0 ? <p className="text-muted italic">No data available</p> : (
                <div className="table-wrapper">
                  <table>
                    <thead>
                      <tr><th>Flat</th><th>Amount</th><th>Status</th><th>Action</th></tr>
                    </thead>
                    <tbody>
                      {maintenance.map(m => (
                        <tr key={m.id}>
                          <td>{m.flat?.flatNumber || '-'}</td><td>{m.amount}</td>
                          <td><span className={`badge badge-${m.status === 'Paid' ? 'success' : 'warning'}`}>{m.status}</span></td>
                          <td>
                            {m.status === 'Pending' ? (
                              <button className="btn btn-success" style={{ padding: '6px 12px' }} onClick={() => handleMarkPaid(m.id)}>Mark Paid</button>
                            ) : <span className="text-success ml-2"><Check size={16} /></span>}
                          </td>
                        </tr>
                      ))}
                    </tbody>
                  </table>
                </div>
              )}
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default AdminDashboard;
