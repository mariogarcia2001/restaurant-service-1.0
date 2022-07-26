import { useEffect, useState } from "react";
import { FaArrowLeft, FaTrash } from "react-icons/fa";
import { Link , useParams} from 'react-router-dom';
import IOrderModel from "../../models/Order";
import OrderService from "../../services/OrderServices";

export const OrderCard = () => {
  const { id }= useParams();

  const [order, setOrder] = useState<IOrderModel>();

  useEffect(() => {
    if (id)
      getOrder(id);
  }, [id]);

  const getOrder = (id: any) => {
    OrderService.retrieve(id)
      .then((response: any) => {
        setOrder(response.data); //Víncula el resultado del servicio con la función del Hook useState
        console.log(response.data);
      })
      .catch((e: Error) => {
        console.log(e);
      });
 };

    return (
      <div>
      { 
        order ? (
          <div>          
          <h2>{order.statusOfOrder}</h2>
          <p>{order.description}</p>
          <ul>
            <li>Mesero encargado : {order.waiter}</li>
          </ul>
          <br />
              <div className="btn-group" role="group">                
                <Link to={"/orders"} className="btn btn-primary">
                    <FaArrowLeft /> Volver
                </Link>
                <button type="button" className="btn btn-danger">
                  <FaTrash />Eliminar
                </button>
              </div>
          </div>

        ) : 
        ( 
          <h1>No hay una orden activa</h1>
        )
      }
      </div>
    );
}

