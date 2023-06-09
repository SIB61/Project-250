import { useState } from 'react';

export default function SettingsForm({ initialSettings }) {
  const [name, setName] = useState(initialSettings.name);
  const [email, setEmail] = useState(initialSettings.email);

  const handleSubmit = async (e) => {
    e.preventDefault();

    const response = await fetch('/api/settings', {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ name, email }),
    });

    const data = await response.json();
    console.log(data);
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label htmlFor="name">Name:</label>
        <input
          type="text"
          id="name"
          value={name}
          onChange={(e) => setName(e.target.value)}
        />
      </div>
      <div>
        <label htmlFor="email">Email:</label>
        <input
          type="email"
          id="email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
      </div>
      <button type="submit">Save</button>
    </form>
  );
}
