import React from 'react';
import Navbar from './components/layout/navigation/Navbar';
import AppRoutes from './AppRoutes';

const App: React.FC = () => {
  return (
    <div className='App'>
      <Navbar />
      <AppRoutes/>
    </div>
  );
};
export default App;
