import { useState } from 'react'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import './App.css'
import Home from './components/home.tsx'

function App() {
  const [count, setCount] = useState(0)

  return (
      <Router>
          <div className="app">
              <Routes>
                  <Route path="/" element={<Home />} />
              </Routes>
          </div>
      </Router>
  )
}

export default App
