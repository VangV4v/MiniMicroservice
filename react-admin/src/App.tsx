import './App.css'
import HeaderPage from './components/header'
import { Route, Routes } from 'react-router-dom'
import LoginPage from './features/login/indext'
import Customers from './features/customers'

function App() {
  return (
    <>
      <HeaderPage></HeaderPage>
      <Routes>
        <Route element={<Customers></Customers>} path='/customers'></Route>
        <Route element={<LoginPage />} path='/login'></Route>
      </Routes>
    </>
  )
}

export default App
