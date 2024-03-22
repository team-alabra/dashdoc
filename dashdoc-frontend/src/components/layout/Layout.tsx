import React from "react";
import Navbar from "./navigation/Navbar";
import Footer from "./Footer";
import { Outlet } from "react-router-dom";
import styled from "styled-components";

const PageContainer = styled.div`
  background-color: #fafafa;
  margin: 2rem;
  overflow: auto;
`;

export const Layout: React.FC<any> = ({ ...props }: any) => {
  return (
    <>
      <Navbar />

      {/* This area shows all of our pages under the NavBar */}
      <PageContainer>
        <Outlet />
      </PageContainer>

      <Footer>@{new Date().getFullYear()} Alabra Productions</Footer>
    </>
  );
};