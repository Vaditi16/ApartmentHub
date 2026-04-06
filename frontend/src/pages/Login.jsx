import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Building2 } from 'lucide-react';

const Login = () => {
  const [role, setRole] = useState('resident');
  const navigate = useNavigate();

  const handleLogin = (e) => {
    e.preventDefault();
    if (role === 'resident') {
      navigate('/resident');
    } else {
      navigate('/admin');
    }
  };

  return (
    <div className="flex justify-center items-center" style={{ minHeight: '100vh', background: 'linear-gradient(135deg, var(--background) 0%, #DBEAFE 100%)' }}>
      <div className="card" style={{ maxWidth: '400px', width: '100%', textAlign: 'center', padding: '40px 24px' }}>
        <div className="flex justify-center mb-4 text-primary" style={{ color: 'var(--primary)' }}>
          <Building2 size={56} />
        </div>
        <h1 className="text-2xl font-bold mb-2">🏢 ApartmentHub</h1>
        <p className="text-muted mb-8 text-lg">Welcome to Smart Apartment Management</p>
        
        <form onSubmit={handleLogin}>
          <div className="flex justify-center gap-6 mb-8">
            <label className="flex items-center gap-2 cursor-pointer p-4 border rounded-xl hover:bg-gray-50 flex-1 transition-all" style={{ border: role === 'resident' ? '2px solid var(--primary)' : '2px solid var(--border)', background: role === 'resident' ? '#EFF6FF' : 'transparent', borderRadius: '12px' }}>
              <input 
                type="radio" 
                name="role" 
                value="resident" 
                checked={role === 'resident'} 
                onChange={() => setRole('resident')}
                style={{ display: 'none' }}
              />
              <div className="font-semibold text-center w-full">Resident</div>
            </label>
            <label className="flex items-center gap-2 cursor-pointer p-4 border rounded-xl hover:bg-gray-50 flex-1 transition-all" style={{ border: role === 'admin' ? '2px solid var(--primary)' : '2px solid var(--border)', background: role === 'admin' ? '#EFF6FF' : 'transparent', borderRadius: '12px' }}>
              <input 
                type="radio" 
                name="role" 
                value="admin" 
                checked={role === 'admin'} 
                onChange={() => setRole('admin')} 
                style={{ display: 'none' }}
              />
              <div className="font-semibold text-center w-full">Admin</div>
            </label>
          </div>
          
          <button type="submit" className="btn full-width" style={{ width: '100%', padding: '12px' }}>
            Continue
          </button>
        </form>
      </div>
    </div>
  );
};

export default Login;
