import './App.css'
import HeaderPage from './components/header'
import { Route, Routes } from 'react-router-dom'
import LoginPage from './features/login/indext'

function App() {
  return (
    <>
      <HeaderPage></HeaderPage>
      <Routes>
        <Route element={<LoginPage />} path='/login'></Route>
      </Routes>
    </>
  )
}

export default App
