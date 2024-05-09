import React from 'react'
import ReactDOM from 'react-dom/client'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import Welcome from './components/Welcome'
import Navbar from './components/Navbar'
import { ChakraProvider } from '@chakra-ui/react'
import ListWidget from './components/Widget/ListWidget'
import CreateWidget from './components/Widget/CreateWidget'

ReactDOM.createRoot(document.getElementById('root')).render(
  <BrowserRouter>
    <ChakraProvider>
      <React.StrictMode>
        <Navbar />
        <Routes>
          <Route path="/" element={<Welcome />}></Route>
          <Route path="createWidget" element={<CreateWidget />}></Route>
          <Route path="updateWidget" element={<CreateWidget />}></Route>
          <Route path="listWidget" element={<ListWidget />}></Route>
        </Routes>
      </React.StrictMode>
    </ChakraProvider>
  </BrowserRouter>
)
