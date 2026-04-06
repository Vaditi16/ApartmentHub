import React from 'react';
import { User } from 'lucide-react';
import { useNavigate } from 'react-router-dom';

const Topbar = ({ onProfileClick }) => {
  const navigate = useNavigate();
  return (
    <nav className="navbar">
      <h1 className="cursor-pointer" onClick={() => navigate('/')}>🏢 ApartmentHub</h1>
      <div className="profile-icon" onClick={onProfileClick}>
        <User size={20} color="white" />
      </div>
    </nav>
  );
};

export default Topbar;
