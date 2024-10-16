import React from 'react';
import * as S from '@styles';

const Logo = () => {
  return (
    <S.NavLink data-testid="nav-logo" to="/">
      <S.Logo>Dashdoc</S.Logo>
    </S.NavLink>
  );
};

export default Logo;
