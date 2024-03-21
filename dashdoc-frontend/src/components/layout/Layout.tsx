import UserDashboard from '@pages/UserDashboard';
import React from 'react';
import Navbar from './navigation/Navbar';
import Footer from './Footer';
import { Outlet } from 'react-router-dom';

export const Layout: React.FC<any> = ({ ...props }: any) => {
  return (
    <> 
      <Navbar />

      <Outlet />

      <Footer>@{new Date().getFullYear()} Alabra Productions</Footer>
    </>
  )
};
