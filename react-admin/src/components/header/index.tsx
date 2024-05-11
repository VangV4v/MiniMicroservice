import { AppBar, Button, Toolbar, Typography } from '@mui/material';
import '../../assets/css/main.css';
import { useAppSelector } from '../../app/hooks';
import { redirect } from 'react-router-dom';

export default function HeaderPage() {
    const authenticationStatus = useAppSelector(state => state.auth.authentication);
    function handleLogout() {
        localStorage.removeItem('jwt');
        redirect('/');
    }
    return (
        <>
            <AppBar className='appbar'>
                <Toolbar>
                    <Button variant='text' href='/' color='inherit'><Typography>ADMIN</Typography></Button>
                    {authenticationStatus ? <Button href='/customers' color='inherit'>Customers</Button> : ''}
                    <Typography sx={{ flexGrow: 1 }} align='left'></Typography>
                    {authenticationStatus ? <Button onClick={() => handleLogout()} color='inherit'>Logout</Button> : ''}
                    {!authenticationStatus ? <Button href='/login' color='inherit'>Login</Button> : ''}
                </Toolbar>
            </AppBar>
        </>
    );
}