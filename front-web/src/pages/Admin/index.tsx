import React from 'react';
import { Route, Switch } from 'react-router-dom';
import Navbar from './components/Navbar';
import Products from './components/Products';
import './styles.scss';

const Admin = () => (
    <div className="admin-container">
        <Navbar/>
        <div className="admin-content">
            <Switch>
                <Route path="/admin/products">
                    <Products/>
                </Route>
                <Route path="/admin/categories">
                    <Products/>
                </Route>
                <Route path="/admin/users">
                    <Products/>
                </Route>
            </Switch>
        </div>
    </div>
);

export default Admin;