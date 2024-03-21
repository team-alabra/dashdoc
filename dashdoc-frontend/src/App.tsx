import React from "react";
import { Routes, Route } from "react-router-dom";
import Login from "@pages/Login";
import UserDashboard from "@pages/UserDashboard";
import AllClients from "@pages/AllClients";
import SingleClient from "@pages/SingleClient";
import { SignUp } from "@pages/SignUp";
import AllDocuments from "@pages/AllDocuments";
import SingleDocument from "@pages/SingleDocument";
import UserAppointments from "@pages/UserAppointments";
import SingleAppointment from "@pages/SingleAppointment";
import UserCalendar from "@pages/UserCalendar";
import Billing from "@pages/payment/Billing";
import PrivateRoute from "@components/PrivateRoute";
import MyAccount from "@pages/MyAccount";
import EmailVerify from "@pages/EmailVerify";
import Success from "@components/layout/Success";
import NotFound from "@pages/NotFound";
import { Layout } from "@components/layout/Layout";

const App: React.FC = () => {
  return (
    <div className="App">
      <Routes>
        {/* PUBLIC ROUTES */}
        <Route path="login" element={<Login />} />
        <Route path="signup" element={<SignUp />} />
        <Route path="emailVerify" element={<EmailVerify />} />
        <Route path="verifySuccess" element={<Success />} />

        {/* PRIVATE ROUTES */}
        <Route
          element={
            <PrivateRoute>
              <Layout />
            </PrivateRoute>
          }
        >
          <Route path="/" element={<UserDashboard />} />
          <Route path="clients" element={<AllClients />} />
          <Route path="clients/:clientId" element={<SingleClient />} />
          <Route path="documents" element={<AllDocuments />} />
          <Route path="documents/:documentId" element={<SingleDocument />} />
          <Route path="appointments" element={<UserAppointments />} />
          <Route
            path="appointments/:appointmentId"
            element={<SingleAppointment />}
          />
          <Route path="calendar" element={<UserCalendar />} />
          <Route path="billing" element={<Billing />} />
          <Route path="myAccount" element={<MyAccount />} />
        </Route>
        <Route path="*" element={<NotFound />} />
      </Routes>
    </div>
  );
};

export default App;
