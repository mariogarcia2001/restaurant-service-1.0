import { Routes, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import { Home } from "./components/Home";
import { OrderList } from "./components/order/OrderList";
import { OrderForm } from "./components/order/OrderForm";
import { OrderCard } from "./components/order/OrderCard";

const title = "Online Test";
const description = "Aplicación web para la automatización de cuestionarios en línea";

const App: React.FC = () => {
  return (
    <div>
      <nav className="navbar navbar-expand navbar-dark bg-dark">        
        <Link to={"/"}  className="navbar-brand">
          NRC 6515
        </Link>
        <div className="navbar-nav mr-auto">
          <li className="nav-item">
            <Link to={"/orders"} className="nav-link">
              Ordenes
            </Link>
          </li>          
        </div>
      </nav>
      <div className="container mt-3">
        <Routes>
          <Route path="/" element={<Home title={title} description={description} />} />          
          <Route path="/orders" element={<OrderList />} />          
          <Route path="/orders/create" element={<OrderForm />} />    
          <Route path="/orders/retrieve/:id" element={<OrderCard/>} />      
          <Route path="/orders/update/:id" element={<OrderForm />} />    
        </Routes>
      </div>
    </div>
  );
}
export default App;


