import { ChangeEvent, useEffect, useState } from "react";
import { FaArrowLeft, FaSave } from "react-icons/fa";
import { Link, useNavigate, useParams } from "react-router-dom";
import IOrderModel from "../../models/Order";
import OrderService from "../../services/OrderServices";

export const OrderForm = () => {
  
  const { id }= useParams();
  let navigate = useNavigate();

    //Modelo vacío
    const initialOrderModel : IOrderModel = {
        id: null,
        description: "",
        waiter:"" ,
        statusOfOrder: ""
    };

    //Hooks para gestionar el modelo
    const [order, setOrder] = useState<IOrderModel>(initialOrderModel);
    
    //Escucha los cambios en cada control Input y los asigna a los valores del Modelo
    const handleInputChange = (event: ChangeEvent<HTMLInputElement>) => {
        const { name, value } = event.target;
        setOrder({ ...order, [name]: value });
    };

    const handleTextAreaChange = (event: ChangeEvent<HTMLTextAreaElement>) => {
      const { name, value } = event.target;
      setOrder({ ...order, [name]: value });
  };

    const saveOrder = () => {        
      if(order.id !== null)
      {
        OrderService.update(order)
        .then((response: any) => {
          navigate("/orders");
          console.log(response.data);
        })
        .catch((e: Error) => {
          console.log(e);
        });
      }
      else
      {
        OrderService.create(order)
          .then((response: any) => {    
            navigate("/orders");
            console.log(response.data);
          })
          .catch((e: Error) => {
            console.log(e);
          });
      }
    };

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

    return ( //JSX
      <div className="submit-form">       
          <div>
            { order.id !== null ? (<h1>Orden Actualizada {order.statusOfOrder}</h1>) : (<h1>Registro de una nueva orden</h1>) }            
            <div className="form-group">
            <label htmlFor="statusOfOrder">Estado de la orden</label>
            <input
              type="text"
              placeholder="Ingrese el estado de la orden"
              className="form-control"
              id="statusOfOrder"
              required
              value={order.statusOfOrder}
              onChange={handleInputChange}
              name="statusOfOrder"
            />
            <label htmlFor="description">Descripcion de la Orden</label>
            <input            
              type="text"
              className="form-control"
              placeholder="Describa la Orden"
              id="description"
              required
              value={order.description}
              onChange={handleInputChange}
              name="description"
            />
            <label htmlFor="waiter">Mesero Encargado</label>
            <input            
              type="text"
              className="form-control"
              placeholder="Mesero a cargo"
              id="waiter"
              required
              value={order.waiter}
              onChange={handleInputChange}
              name="waiter"
            />
            <br />
              <div className="btn-group" role="group">                
                <Link to={"/orders"} className="btn btn-primary">
                    <FaArrowLeft /> Volver
                </Link>
                <button type="button" onClick={saveOrder} className="btn btn-success">
                  <FaSave />Guardar
                </button>
              </div>
            </div>
          </div>        
      </div>        
    );

}

