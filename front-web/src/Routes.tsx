import React from "react";
import { BrowserRouter, Switch, Route } from "react-router-dom";
import Orders from "./Orders"
import Home from "./Home"
import Navbar from "./Navbar"

function Routes() {
    return (
        // Componente do React Router DOM q gerencia as rotas. O React Router DOM trampa assim
        <BrowserRouter>
        <Navbar />
            <Switch>
                <Route path="/orders">
                    <Orders />
                </Route>
                <Route path="/">
                    <Home />
                </Route>
            </Switch>
        </BrowserRouter>
    )
}

export default Routes;