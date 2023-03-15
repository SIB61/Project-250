import { useState } from 'react';

function MyComponent() {
  const [users, setUsers] = useState([]);

  async function searchUsers(searchTerm) {
    const response = await fetch(`/api/search-users?search=${searchTerm}`);
    const data = await response.json();
    setUsers(data);
  }

  return (
    <div>
      <input type="text" onChange={(e) => searchUsers(e.target.value)} />
      {users.map(user => <div key={user.id}>{user.name}</div>)}
    </div>
  );
}
