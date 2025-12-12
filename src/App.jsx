import { Routes, Route } from 'react-router-dom'
import { AuthProvider } from './Components/Auth/AuthContext'
import Home from './Pages/Home'
import Login from './Pages/Login'
import Register from './Pages/Register'
import './styles/global.css'

function App() {
    return (
        <AuthProvider>
            <Routes>  {/* <-- Utilise Routes directement, sans Router */}
                <Route path="/" element={<Home />} />
                <Route path="/login" element={<Login />} />
                <Route path="/register" element={<Register />} />
            </Routes>
        </AuthProvider>
    )
}

export default App
