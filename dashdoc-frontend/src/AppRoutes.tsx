import React from 'react';
import { Routes, Route } from 'react-router-dom';
import AboutUs from './pages/AboutUs';
import LandingPage from './pages/LandingPage';
import Login from './pages/Login';
import UserDashboard from './pages/UserDashboard';
import AllClients from './pages/AllClients';
import SingleClient from './pages/SingleClient';
import SignUp from './pages/SignUp';
import AllDocuments from './pages/AllDocuments';
import SingleDocument from './pages/SingleDocument';
import SubscriptionPlans from './pages/SubscriptionPlans';
import UserAppointments from './pages/UserAppointments';
import SingleAppointment from './pages/SingleAppointment';
import UserCalendar from './pages/UserCalendar';
import Billing from './pages/payment/Billing';
import FreeTrial from './pages/FreeTrial';
import ContactUs from './pages/ContactUs';
import PrivateRoute from './components/PrivateRoute';
import MyAccount from './pages/MyAccount';

const AppRoutes: React.FC = () => {
  return (
    <Routes>
      <Route path='/' element={<LandingPage />} />
      <Route path='/aboutUs' element={<AboutUs />} />
      <Route path='/login' element={<Login />} />
      <Route path='/signup' element={<SignUp />} />
      <Route path='/subscriptionPlans' element={<SubscriptionPlans />} />
      <Route path='/freeTrial' element={<FreeTrial />} />
      <Route path='/contactUs' element={<ContactUs />} />
      <Route
        path='/dashboard'
        element={
          <PrivateRoute exact path="/dashboard">
            <UserDashboard />
          </PrivateRoute>
        }
      />
      <Route path='/dashboard/clients' element={<AllClients />} />
      <Route path='/dashboard/clients/:clientId' element={<SingleClient />} />
      <Route path='/dashboard/documents' element={<AllDocuments />} />
      <Route
        path='/dashboard/documents/:documentId'
        element={<SingleDocument />}
      />
      <Route path='/dashboard/appointments' element={<UserAppointments />} />
      <Route
        path='/dashboard/appointments/:appointmentId'
        element={<SingleAppointment />}
      />
      <Route path='/dashboard/calendar' element={<UserCalendar />} />
      <Route path='/dashboard/billing' element={<Billing />} />
      <Route path='/dashboard/myAccount' element={<MyAccount />} />
    </Routes>
  );
};

export default AppRoutes;
