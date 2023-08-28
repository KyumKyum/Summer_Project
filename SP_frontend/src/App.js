import './App.css';
import Header from './Header';
import Home from './Home';
import Generator from './Generator';
import Maker from './Maker';
import Login from './Login';
import Signup from './Signup';
import { BrowserRouter as Router, Routes, Route} from "react-router-dom";
import {useEffect, useState} from "react";

function App() {

  const [loggedIn, setLoggedIn] = useState(false);

  useEffect(() => {
  }, [loggedIn])

  return (
    <Router>
      <div className='App'>

        <Routes>
          <Route exact path='/' element={
            <div>
              <Header loggedIn={loggedIn}/>
              <Home
                checkLogin = {setLoggedIn}
                loggedIn = {loggedIn}/>
            </div>} />
          <Route exact path='/generator' element={<div><Header loggedIn={loggedIn}/><Generator/></div>} />
          <Route exact path='/maker' element={<div><Header loggedIn={loggedIn}/><Maker/></div>} />
          <Route exact path='/login' element={<Login/>}/>
          <Route exact path='/signup' element={<Signup/>}/>
        </Routes>

      </div>
    </Router>
  );
}

export default App;
