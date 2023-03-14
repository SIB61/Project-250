export default function handler(req, res) {
    const { method } = req;
  
    switch (method) {
      case 'GET':
        // Get user settings from the database and return them as JSON
        res.status(200).json({ name: 'John Doe', email: 'johndoe@example.com' });
        break;
      case 'PUT':
        // Update user settings in the database and return the updated settings as JSON
        const { name, email } = req.body;
        // Update user settings in the database here
        res.status(200).json({ name, email });
        break;
      default:
        res.setHeader('Allow', ['GET', 'PUT']);
        res.status(405).end(`Method ${method} Not Allowed`);
    }
  }
  