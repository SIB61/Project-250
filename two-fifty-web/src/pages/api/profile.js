let profiles = [
    { id: 1, name: 'John Doe', email: 'johndoe@example.com', bio: 'I am a web developer' },
    { id: 2, name: 'Jane Doe', email: 'janedoe@example.com', bio: 'I am a graphic designer' },
  ];
  
  export default function handler(req, res) {
    const { method } = req;
  
    switch (method) {
      case 'GET':
        // Get all profiles from the database and return them as JSON
        res.status(200).json(profiles);
        break;
      case 'POST':
        // Create a new profile in the database and return the new profile as JSON
        const { name, email, bio } = req.body;
        const newProfile = { id: profiles.length + 1, name, email, bio };
        profiles.push(newProfile);
        res.status(201).json(newProfile);
        break;
      case 'PUT':
        // Update a profile in the database and return the updated profile as JSON
        const { id, updatedProfile } = req.body;
        const index = profiles.findIndex((p) => p.id === id);
        profiles[index] = { ...profiles[index], ...updatedProfile };
        res.status(200).json(profiles[index]);
        break;
      case 'DELETE':
        // Delete a profile from the database
        const { id: deleteId } = req.body;
        profiles = profiles.filter((p) => p.id !== deleteId);
        res.status(200).json({ message: 'Profile deleted successfully' });
        break;
      default:
        res.setHeader('Allow', ['GET', 'POST', 'PUT', 'DELETE']);
        res.status(405).end(`Method ${method} Not Allowed`);
    }
  }
  