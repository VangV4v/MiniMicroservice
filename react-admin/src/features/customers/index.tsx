import React, { useEffect, useState } from 'react';
import Box from '@mui/material/Box';
import { DataGrid, GridColDef } from '@mui/x-data-grid';
import { useAppSelector } from '../../app/hooks';
import HeaderRequest from '../../models/HeaderRequest';
import customerApi from '../../api/CustomerApi';
import { set } from 'react-hook-form';

const columns: GridColDef<(typeof rows)[number]>[] = [
    { field: 'customerid', headerName: 'CUSTOMERID', width: 90 },
    {
        field: 'firstName',
        headerName: 'First name',
        width: 150,
        editable: true,
    },
    {
        field: 'lastName',
        headerName: 'Last name',
        width: 150,
        editable: true,
    },
    {
        field: 'age',
        headerName: 'Age',
        type: 'number',
        width: 110,
        editable: true,
    },
    {
        field: 'fullName',
        headerName: 'Full name',
        description: 'This column has a value getter and is not sortable.',
        sortable: false,
        width: 160,
        valueGetter: (value, row) => `${row.firstName || ''} ${row.lastName || ''}`,
    },
];

const rows = [
    { id: 1, customerid: 1, lastName: 'Snow', firstName: 'Jon', age: 14 },
];

export default function Customers() {

    const jwt = useAppSelector(state => state.auth.jwt);
    const header: HeaderRequest = {
        authorization: jwt
    };
    const [data, setData] = useState();
    useEffect(() => {
        async function fetchData() {
            const response = await customerApi.getAlls(header);
            setData(response.data);
            console.log(response);
            console.log(data);
        }
        fetchData();
    }, []);

    return (
        <Box sx={{ height: 400, width: '100%' }}>
            <DataGrid
                rows={rows}
                columns={columns}
                initialState={{
                    pagination: {
                        paginationModel: {
                            pageSize: 5,
                        },
                    },
                }}
                pageSizeOptions={[5]}
                checkboxSelection
                disableRowSelectionOnClick
            />
        </Box>
    );
}