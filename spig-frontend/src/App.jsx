import React, { useState } from 'react';
import Sifrarnik from './components/Sifrarnik';
import MasterDetail from './components/MasterDetail';

function App() {
  const [activeTab, setActiveTab] = useState('master-detail');

  return (
    <div className="app-container">
      <header className="header">
        <h1>SPIG Sustav</h1>
        <div className="nav-buttons">
          <button 
            className={activeTab === 'master-detail' ? 'active' : ''} 
            onClick={() => setActiveTab('master-detail')}
          >
            Utakmice (Master-Detail)
          </button>
          <button 
            className={activeTab === 'sifrarnik' ? 'active' : ''} 
            onClick={() => setActiveTab('sifrarnik')}
          >
            Sportovi (Šifrarnik)
          </button>
        </div>
      </header>

      <main>
        {activeTab === 'master-detail' && <MasterDetail />}
        {activeTab === 'sifrarnik' && <Sifrarnik />}
      </main>
    </div>
  );
}

export default App;
