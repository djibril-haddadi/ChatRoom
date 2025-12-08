import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import { AuthProvider } from './Components/utils/authContext.jsx'
import Home from './Pages/Home.jsx'
import Register from './Components/auth/Register.jsx'
import SalonList from './Components/dashboard/SalonList.jsx'


function App() {
    return (
        <Router>
            <AuthProvider>
                <Routes>
                    <Route path="/" element={<Home />} />
                    <Route path="/register" element={<Register />}/>
                    <Route path="/salonList" element={<SalonList />}/>
                </Routes>
            </AuthProvider>
        </Router>
    )
}

export default App
