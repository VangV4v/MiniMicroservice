import { Visibility, VisibilityOff } from '@mui/icons-material';
import { IconButton, TextField } from '@mui/material';
import React, { useState } from 'react';
import { Controller } from 'react-hook-form';

export interface PasswordFieldCPProps {
    name: string,
    label: string,
    control: object
}

export default function PasswordFieldCP({ name, label, control }: PasswordFieldCPProps) {

    const [isShowPassword, setShowPassword] = useState(false);
    function handleShowPassword() {
        setShowPassword(!isShowPassword);
    }
    return (
        <Controller
            name={name}
            control={control}
            render={({ field, fieldState }) => (
                <TextField
                    type={!isShowPassword ? 'password' : 'text'}
                    {...field}
                    label={label}
                    error={!!fieldState.error}
                    helperText={fieldState.error?.message}
                    variant="standard"
                    fullWidth
                    InputProps={{
                        endAdornment: <IconButton onClick={handleShowPassword}>{!isShowPassword ? <Visibility /> : <VisibilityOff />}</IconButton>
                    }}
                />
            )
            }
        />
    );
}
