import { TextField } from '@mui/material';
import React from 'react';
import { Controller } from 'react-hook-form';

export interface TextFieldCPProps {
    name: string,
    label: string,
    control: object
}

export default function TextFieldCP({ name, label, control }: TextFieldCPProps) {
    return (
        <Controller
            name={name}
            control={control}
            render={({ field, fieldState }) => (
                <TextField
                    {...field}
                    label={label}
                    error={!!fieldState.error}
                    helperText={fieldState.error?.message}
                    variant="standard"
                    fullWidth
                />
            )}
        />
    );
}
