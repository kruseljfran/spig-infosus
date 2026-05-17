import React, { useState, useEffect } from 'react';

const API_URL = 'http://localhost:8080/api';

export default function MasterDetail() {
  const [utakmice, setUtakmice] = useState([]);
  const [sportovi, setSportovi] = useState([]);
  const [pozicije, setPozicije] = useState([]);
  const [odabranaUtakmica, setOdabranaUtakmica] = useState(null);
  const [prijave, setPrijave] = useState([]);

  const [novaLokacija, setNovaLokacija] = useState('');
  const [noviDatum, setNoviDatum] = useState('');
  const [noviKapacitet, setNoviKapacitet] = useState('');
  const [odabraniSportId, setOdabraniSportId] = useState('');

  const [novoImeIgraca, setNovoImeIgraca] = useState('');
  const [odabranaPozicijaId, setOdabranaPozicijaId] = useState('');

  const [pretrazivanje, setPretrazivanje] = useState('');

  const [urediUtakmicuId, setUrediUtakmicuId] = useState(null);
  const [urediPrijavuId, setUrediPrijavuId] = useState(null);

  useEffect(() => {
    fetchUtakmice();
    fetchSportovi();
    fetchPozicije();
  }, []);

  const fetchUtakmice = async () => {
    const res = await fetch(`${API_URL}/utakmice`);
    setUtakmice(await res.json());
  };

  const fetchSportovi = async () => {
    const res = await fetch(`${API_URL}/sportovi`);
    setSportovi(await res.json());
  };

  const fetchPozicije = async () => {
    const res = await fetch(`${API_URL}/pozicije`);
    setPozicije(await res.json());
  };

  const handleSelectUtakmica = async (utakmica) => {
    setOdabranaUtakmica(utakmica);
    const res = await fetch(`${API_URL}/prijave/utakmica/${utakmica.id}`);
    setPrijave(await res.json());
  };

  const handleKreirajIliAzurirajUtakmicu = async (e) => {
    e.preventDefault();
    const sport = sportovi.find(s => s.id === parseInt(odabraniSportId));
    if (!sport) return alert('Odaberite sport!');

    const data = {
      lokacija: novaLokacija,
      datumVrijeme: noviDatum,
      kapacitet: parseInt(noviKapacitet),
      sport: sport
    };

    if (urediUtakmicuId) {
      await fetch(`${API_URL}/utakmice/${urediUtakmicuId}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
      });
      setUrediUtakmicuId(null);
    } else {
      await fetch(`${API_URL}/utakmice`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
      });
    }
    
    setNovaLokacija(''); setNoviDatum(''); setNoviKapacitet(''); setOdabraniSportId('');
    fetchUtakmice();
  };

  const zapocniUredivanjeUtakmice = (u) => {
    setUrediUtakmicuId(u.id);
    setNovaLokacija(u.lokacija);
    setNoviDatum(u.datumVrijeme);
    setNoviKapacitet(u.kapacitet);
    setOdabraniSportId(u.sport ? u.sport.id : '');
  };

  const handleObrisiUtakmicu = async (id) => {
    if (odabranaUtakmica && odabranaUtakmica.id === id) setOdabranaUtakmica(null);
    await fetch(`${API_URL}/utakmice/${id}`, { method: 'DELETE' });
    fetchUtakmice();
  };

  const handlePrijaviIliAzurirajIgraca = async (e) => {
    e.preventDefault();
    if (!odabranaUtakmica || !novoImeIgraca.trim()) return;

    if (urediPrijavuId) {
      await fetch(`${API_URL}/prijave/${urediPrijavuId}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ imeIgraca: novoImeIgraca, pozicijaId: odabranaPozicijaId })
      });
      setUrediPrijavuId(null);
    } else {
      await fetch(`${API_URL}/prijave/utakmica/${odabranaUtakmica.id}`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ imeIgraca: novoImeIgraca, pozicijaId: odabranaPozicijaId })
      });
    }

    setNovoImeIgraca('');
    setOdabranaPozicijaId('');
    handleSelectUtakmica(odabranaUtakmica);
    fetchUtakmice();
  };

  const zapocniUredivanjePrijave = (p) => {
    setUrediPrijavuId(p.id);
    setNovoImeIgraca(p.imeIgraca);
    setOdabranaPozicijaId(p.pozicija ? p.pozicija.id : '');
  };

  const handleObrisiPrijavu = async (id) => {
    await fetch(`${API_URL}/prijave/${id}`, { method: 'DELETE' });
    handleSelectUtakmica(odabranaUtakmica);
    fetchUtakmice();
  };

  return (
    <div className="master-detail-grid">

      <div className="glass-card">
        <h2 style={{ marginBottom: '1.5rem' }}>1. Zaglavlje (Master): Utakmice</h2>

        <form onSubmit={handleKreirajIliAzurirajUtakmicu} style={{ background: 'rgba(0,0,0,0.05)', padding: '1rem', borderRadius: '8px', marginBottom: '1.5rem' }}>
          <h3 style={{ marginBottom: '1rem', fontSize: '1rem', color: '#2980b9' }}>
            {urediUtakmicuId ? "Ažuriraj Utakmicu" : "Nova Utakmica"}
          </h3>
          <div className="form-group">
            <label>Lokacija</label>
            <input type="text" value={novaLokacija} onChange={e => setNovaLokacija(e.target.value)} required />
          </div>
          <div className="form-group">
            <label>Datum i vrijeme</label>
            <input type="datetime-local" value={noviDatum} onChange={e => setNoviDatum(e.target.value)} required />
          </div>
          <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '1rem' }}>
            <div className="form-group">
              <label>Kapacitet</label>
              <input type="number" min="1" value={noviKapacitet} onChange={e => setNoviKapacitet(e.target.value)} required />
            </div>
            <div className="form-group">
              <label>Sport (Strani ključ)</label>
              <select value={odabraniSportId} onChange={e => setOdabraniSportId(e.target.value)} required>
                <option value="">-- Odaberi --</option>
                {sportovi.map(s => <option key={s.id} value={s.id}>{s.naziv}</option>)}
              </select>
            </div>
          </div>
          <div style={{ display: 'flex', gap: '10px' }}>
            <button type="submit" className="btn btn-primary" style={{ flex: 1 }}>
              {urediUtakmicuId ? "Spremi Promjene" : "Kreiraj Utakmicu"}
            </button>
            {urediUtakmicuId && (
              <button type="button" className="btn" onClick={() => { setUrediUtakmicuId(null); setNovaLokacija(''); setNoviDatum(''); setNoviKapacitet(''); setOdabraniSportId(''); }}>
                Odustani
              </button>
            )}
          </div>
        </form>

        <div className="form-group">
          <input 
            type="text" 
            placeholder="Pretraži utakmice po lokaciji ili sportu..." 
            value={pretrazivanje}
            onChange={e => setPretrazivanje(e.target.value)}
          />
        </div>

        <div style={{ overflowX: 'auto' }}>
          <table>
            <thead>
              <tr>
                <th>Lokacija</th>
                <th>Sport</th>
                <th>Popunjenost</th>
                <th style={{ textAlign: 'right' }}>Akcije</th>
              </tr>
            </thead>
            <tbody>
              {utakmice
                .filter(u => 
                  u.lokacija.toLowerCase().includes(pretrazivanje.toLowerCase()) ||
                  (u.sport && u.sport.naziv.toLowerCase().includes(pretrazivanje.toLowerCase()))
                )
                .map(u => (
                <tr
                  key={u.id}
                  className={`item-row ${odabranaUtakmica?.id === u.id ? 'selected' : ''}`}
                  onClick={() => handleSelectUtakmica(u)}
                >
                  <td>{u.lokacija}</td>
                  <td>{u.sport?.naziv}</td>
                  <td>
                    <span className={`badge ${u.trenutnoPrijavljenih >= u.kapacitet ? 'badge-warning' : 'badge-success'}`}>
                      {u.trenutnoPrijavljenih} / {u.kapacitet}
                    </span>
                  </td>
                  <td style={{ textAlign: 'right' }}>
                    <button onClick={(e) => { e.stopPropagation(); zapocniUredivanjeUtakmice(u); }} className="btn btn-primary" style={{ marginRight: '5px', padding: '4px 8px', fontSize: '12px' }}>Uredi</button>
                    <button onClick={(e) => { e.stopPropagation(); handleObrisiUtakmicu(u.id); }} className="btn btn-danger" style={{ padding: '4px 8px', fontSize: '12px' }}>Obriši</button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>

      <div className="glass-card">
        <h2 style={{ marginBottom: '1.5rem' }}>2. Detalji (Prijave)</h2>

        {!odabranaUtakmica ? (
          <div style={{ textAlign: 'center', color: 'var(--text-muted)', marginTop: '3rem' }}>
            <p>Odaberite utakmicu iz tablice lijevo za pregled prijava.</p>
          </div>
        ) : (
          <>
            <div style={{ marginBottom: '2rem', padding: '1rem', borderLeft: '4px solid var(--primary)', background: 'rgba(59, 130, 246, 0.05)' }}>
              <h3>{odabranaUtakmica.lokacija} ({odabranaUtakmica.sport?.naziv})</h3>
              <p style={{ color: 'var(--text-muted)', fontSize: '0.875rem', marginTop: '0.5rem' }}>
                Trenutno prijavljeno: {odabranaUtakmica.trenutnoPrijavljenih} od {odabranaUtakmica.kapacitet}
              </p>
            </div>

            <form onSubmit={handlePrijaviIliAzurirajIgraca} style={{ display: 'flex', gap: '1rem', marginBottom: '2rem' }}>
              <input
                type="text"
                placeholder="Ime i prezime igrača"
                value={novoImeIgraca}
                onChange={e => setNovoImeIgraca(e.target.value)}
                required
              />
              <select value={odabranaPozicijaId} onChange={e => setOdabranaPozicijaId(e.target.value)} required>
                <option value="">-- Pozicija --</option>
                {pozicije.map(p => <option key={p.id} value={p.id}>{p.naziv}</option>)}
              </select>
              <button type="submit" className="btn btn-primary">
                {urediPrijavuId ? "Ažuriraj" : "Prijavi se"}
              </button>
              {urediPrijavuId && (
                <button type="button" className="btn" onClick={() => { setUrediPrijavuId(null); setNovoImeIgraca(''); setOdabranaPozicijaId(''); }}>Odustani</button>
              )}
            </form>

            <table>
              <thead>
                <tr>
                  <th>Ime Igrača</th>
                  <th>Pozicija</th>
                  <th>Status</th>
                  <th style={{ textAlign: 'right' }}>Akcije</th>
                </tr>
              </thead>
              <tbody>
                {prijave.map(p => (
                  <tr key={p.id}>
                    <td>{p.imeIgraca}</td>
                    <td>{p.pozicija ? p.pozicija.naziv : '-'}</td>
                    <td>
                      <span className={`badge ${p.status === 'POTVRDENA' ? 'badge-success' : 'badge-warning'}`}>
                        {p.status.replace('_', ' ')}
                      </span>
                    </td>
                    <td style={{ textAlign: 'right' }}>
                      <button onClick={() => zapocniUredivanjePrijave(p)} className="btn btn-primary" style={{ marginRight: '5px', padding: '4px 8px', fontSize: '12px' }}>Uredi</button>
                      <button onClick={() => handleObrisiPrijavu(p.id)} className="btn btn-danger" style={{ padding: '4px 8px', fontSize: '12px' }}>Obriši</button>
                    </td>
                  </tr>
                ))}
                {prijave.length === 0 && (
                  <tr><td colSpan="2" style={{ textAlign: 'center', color: 'var(--text-muted)' }}>Nema prijava za ovu utakmicu.</td></tr>
                )}
              </tbody>
            </table>
          </>
        )}
      </div>

    </div>
  );
}
