import { users } from '../../data'; 

export default async function handler(req, res) {
  const { search } = req.query; 
  const filteredUsers = users.filter(user => user.name.toLowerCase().includes(search.toLowerCase())); // filter the users array based on the search term
  res.status(200).json(filteredUsers); // respond with the filtered users array as a JSON object
}
