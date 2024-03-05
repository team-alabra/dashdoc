import styled from 'styled-components';
import { Link } from 'react-router-dom';

export const Bar = styled.nav`
  font-size: 18px;
  background-color: var(--main-blue);
  padding-bottom: 10px;
  width: 100%;
  z-index: 100;
  top: 0;
  left: 0;
  @media (min-width: 768px) {
    display: flex;
    justify-content: space-between;
    padding-bottom: 0;
    height: 70px;
    align-items: center;
  }
`;

export const MainNav = styled.ul`
  list-style-type: none;
  @media (min-width: 768px) {
    display: flex !important;
    margin-right: 30px;
    flex-direction: row;
    justify-content: flex-end;
  }
`;

export const NavLink = styled(Link)`
  list-style-type: none;
  display: flex;
  flex-direction: column;
  color: white;
  text-decoration: none;
  font-size: 15px;
  @media (min-width: 768px) {
    margin: 0px 10px;
  }
`;

export const NavLiElement = styled.li`
  text-align: center;
  margin: 15px auto;
  margin-left: 20px;
  text-decoration: none;
`;
