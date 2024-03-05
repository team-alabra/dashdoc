import React from 'react';
import Logo from './Logo';
import * as S from '../../../styles';
import NavLinks from './NavLinks';
import { NAVBAR_DATA } from '@constants';

const Navbar: React.FC<any> = ({ ...props }: any) => {

  return (
    <S.Bar data-testid='nav-bar-container'>
      <Logo />
      <NavLinks navbarData={NAVBAR_DATA} />
    </S.Bar>
  );
};

export default Navbar;
