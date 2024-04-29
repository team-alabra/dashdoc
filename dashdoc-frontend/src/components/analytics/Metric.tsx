import React from 'react';
import * as S from '@styles';
import Stat from './Stat';
import { Analytics } from '@typings/analytics';

const Metric = ({ title, data }: Analytics) => {
  return (
    <>
      <div className='metric-container'>
        <S.StyledHeader fontSize='20' fontColor='black' fontWeight='bold'>
          {data}
        </S.StyledHeader>

        <S.StyledHeader fontSize='14' fontColor='black'>
          {title}
        </S.StyledHeader>

        <Stat />
      </div>

      <hr className='vline'></hr>
    </>
  );
};

export default Metric;
