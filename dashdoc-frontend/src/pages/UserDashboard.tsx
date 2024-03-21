import Footer from "@components/layout/Footer";
import React from "react";
import { useSelector } from "react-redux";

const UserDashboard = () => {
  const user = useSelector((state: any) => state.user);
  return (
    <div data-testid="dashboard-container">
      <h1>Welcome, {user.providerDetail.firstName}</h1>
    </div>
  );
};

export default UserDashboard;
