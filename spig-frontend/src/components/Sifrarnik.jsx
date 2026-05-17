import React, { useState, useEffect } from 'react';

const API_URL = 'http://localhost:8080/api';

export default function Sifrarnik() {
  const [sportovi, setSportovi] = useState([]);
  const [noviSport, setNoviSport] = useState('');
  const [pretrazivanje, setPretrazivanje] = useState('');
  const [urediId, setUrediId] = useState(null);

  useEffect(() => {
    fetchSportovi();
  }, []);

  const fetchSportovi = async () => {
    try {
      const res = await fetch(`${API_URL}/sportovi`);
      const data = await res.json();
      setSportovi(data);
    } catch (err) {
      console.error("Error fetching sports:", err);
    }
  };

  const handleSpremi = async (e) => {
    e.preventDefault();
    if (!noviSport.trim()) return;

    try {
      if (urediId) {
        await fetch(`${API_URL}/sportovi/${urediId}`, {
          method: 'PUT',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({ naziv: noviSport })
        });
        setUrediId(null);
      } else {
        await fetch(`${API_URL}/sportovi`, {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({ naziv: noviSport })
        });
      }
      setNoviSport('');
      fetchSportovi();
    } catch (err) {
      console.error("Greska pri spremanju:", err);
    }
  };

  const zapocniUredivanje = (sport) => {
    setUrediId(sport.id);
    setNoviSport(sport.naziv);
  };

  const handleObrisi = async (id) => {
    try {
      await fetch(`${API_URL}/sportovi/${id}`, { method: 'DELETE' });
      fetchSportovi();
    } catch (err) {
      console.error("Error deleting sport:", err);
    }
  };

  return (
    <div className="glass-card">
      <h2 style={{ marginBottom: '1.5rem' }}>Šifrarnik Sportova</h2>
      
      <form onSubmit={handleSpremi} style={{ display: 'flex', gap: '1rem', marginBottom: '2rem' }}>
        <input 
          type="text" 
          placeholder={urediId ? "Izmijenite naziv sporta..." : "Unesite naziv novog sporta..."} 
          value={noviSport}
          onChange={(e) => setNoviSport(e.target.value)}
        />
        <button type="submit" className="btn btn-primary">
          {urediId ? "Ažuriraj Sport" : "Dodaj Sport"}
        </button>
        {urediId && (
          <button type="button" className="btn" onClick={() => { setUrediId(null); setNoviSport(''); }}>
            Odustani
          </button>
        )}
      </form>

      <div className="form-group">
        <input 
          type="text" 
          placeholder="Pretraži sportove..." 
          value={pretrazivanje}
          onChange={e => setPretrazivanje(e.target.value)}
        />
      </div>

      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Naziv Sporta</th>
            <th style={{ textAlign: 'right' }}>Akcije</th>
          </tr>
        </thead>
        <tbody>
          {sportovi
            .filter(s => s.naziv.toLowerCase().includes(pretrazivanje.toLowerCase()))
            .map(s => (
            <tr key={s.id}>
              <td>{s.id}</td>
              <td>{s.naziv}</td>
              <td style={{ textAlign: 'right' }}>
                <button onClick={() => zapocniUredivanje(s)} className="btn btn-primary" style={{ marginRight: '0.5rem' }}>Uredi</button>
                <button onClick={() => handleObrisi(s.id)} className="btn btn-danger">Obriši</button>
              </td>
            </tr>
          ))}
          {sportovi.length === 0 && (
            <tr><td colSpan="3" style={{ textAlign: 'center', color: 'var(--text-muted)' }}>Nema unesenih sportova.</td></tr>
          )}
        </tbody>
      </table>
    </div>
  );
}
