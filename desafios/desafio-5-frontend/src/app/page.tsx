"use client";
import { AppContextProvider } from './AppContext';
import Dashboard from './components/Dashboard';

export default function Home() {
  return (
    <AppContextProvider>
      <Dashboard/>
    </AppContextProvider>
  );
};