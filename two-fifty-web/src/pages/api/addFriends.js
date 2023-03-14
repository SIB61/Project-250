// pages/api/friends.js

const friends = [
    {
      id: 1,
      name: 'Alice',
      age: 25,
      email: 'alice@example.com',
    },
    {
      id: 2,
      name: 'Bob',
      age: 30,
      email: 'bob@example.com',
    },
    {
      id: 3,
      name: 'Charlie',
      age: 35,
      email: 'charlie@example.com',
    },
  ];
  
  export default function handler(req, res) {
    if (req.method === 'GET') {
      res.status(200).json(friends);
    } else {
      res.status(405).json({ message: 'Method not allowed' });
    }
  }
  