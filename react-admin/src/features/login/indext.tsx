import TextFieldCP from '../../components/input/textfield';
import { useForm } from 'react-hook-form';
import PasswordFieldCP from '../../components/input/password';
import { Button, FormHelperText } from '@mui/material';
import { Send } from '@mui/icons-material';
import { yupResolver } from "@hookform/resolvers/yup"
import * as yup from "yup"
import LoginModel from '../../models/LoginModel';
import authAdminApi from '../../api/AuthAdminApi';
import { useState } from 'react';
import { useAppDispatch } from '../../app/hooks';
import { authAdmin } from '../../app/slice/AuthSlice';

const schema = yup
    .object({
        username: yup.string().required("Please enter your Email or Phone"),
        password: yup.string().required("Please enter your Password"),
    })
    .required();

export default function LoginPage() {
    const [isShowErrLogin, setShowErrLogin] = useState(false);
    const form = useForm({
        defaultValues: {
            username: '',
            password: ''
        },
        resolver: yupResolver(schema)
    });
    const dispatch = useAppDispatch();
    const handleLoginSubmit = async (data: LoginModel) => {
        dispatch(authAdmin(data)).unwrap().catch(function (err) {
            setShowErrLogin(true);
        });
    };
    return (
        <div>
            <form onSubmit={form.handleSubmit(handleLoginSubmit)}>
                <TextFieldCP name='username' label='Email or Phone' control={form.control} />
                <PasswordFieldCP name='password' label='Pasword' control={form.control} />
                {isShowErrLogin ? <FormHelperText error={true}>Incorrect Email, Phone or Password</FormHelperText> : ""}
                <Button style={{ marginTop: '10px' }} type='submit' variant='contained' endIcon={<Send />}>LOGIN</Button>
            </form>
        </div>
    );
}
