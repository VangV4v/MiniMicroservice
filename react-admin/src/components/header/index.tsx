import { AppBar, Button, Toolbar, Typography } from '@mui/material';
import '../../assets/css/main.css';

export default function HeaderPage() {
    return (
        <>
            <AppBar className='appbar'>
                <Toolbar>
                    <Typography sx={{ flexGrow: 1 }} align='left'>ADMIN</Typography>
                    <Button href='/login' color='inherit'>Login</Button>
                    <Button color='inherit'>Logout</Button>
                </Toolbar>
            </AppBar>
        </>
    );
}