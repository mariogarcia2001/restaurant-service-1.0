import { FaPen, FaEye, FaTrash, FaPlus } from "react-icons/fa";
import React, { useState, useEffect } from 'react'
import { Link } from 'react-router-dom';
import IOrderModel from '../../models/Order';
import OrderService from '../../services/OrderServices';
import Swal from "sweetalert2";
import ReactPaginate from "react-paginate";

export const OrderList = () => {

    //Hook: Define un atributo y la función que lo va a actualizar
    const [orders, setOrders] = useState<Array<IOrderModel>>([]);
    const [itemsCount, setItemsCount] = useState<number>(0);
    const [pageCount, setPageCount] = useState(0);
    const [itemsPerPage, setItemsPerPage] = useState(5);
    
    //Hook para llamar a la Web API
    useEffect(() => {
      getItems();  
      listOrders(0, itemsPerPage);           
      }, []);

    const handlePageClick = (event: any) => {        
      const numberPage = event.selected;                   
      listOrders(numberPage, itemsPerPage);
    };

    //Función que llama al Service para listar los datos desde la Web API
    const listOrders = (page: number, size: number) => {
       OrderService.list(page, size)
         .then((response: any) => {
           setOrders(response.data); //Víncula el resultado del servicio con la función del Hook useState
           console.log(response.data);
         })
         .catch((e: Error) => {
           console.log(e);
         });
    };

    const getItems = () => {
      OrderService.count().then((response: any) =>{
        var itemsCount = response;
        setItemsCount(itemsCount);
        setPageCount(Math.ceil(itemsCount/ itemsPerPage));           
        setItemsPerPage(5)
        console.log(response);
      }).catch((e : Error)=> {
        console.log(e);
      });
    }

    const removeOrder = (id: number) => {
        Swal.fire({
            title: '¿Desea eliminar la orden?',
            showDenyButton: true,
            confirmButtonText: 'Si',
            denyButtonText: 'No',
          }).then((result) => {            
            if (result.isConfirmed) {
                OrderService.remove(id)
                .then((response: any) => {
                  listOrders(0,itemsPerPage);
                  console.log(response.data);
                })
                .catch((e: Error) => {
                  console.log(e);
                });      

            }
          });        
     };
   
    return ( 
        <div className='list row'>
            <h1>Hay {itemsCount} ordenes</h1>
            <div className='col-md-12'>
                <table className='table'>
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Estado de la Orden</th>
                            <th>Descripción</th>
                            <th>Mesero Encargado</th>
                            <th>
                              <Link to={"/orders/create"} className="btn btn-success">
                                  <FaPlus /> Agregar
                              </Link>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        {orders && orders.map((Order, index) => (                          
                            <tr key={index}>
                                <td>{++index}</td>
                                <td>{Order.statusOfOrder}</td>
                                <td>{Order.description}</td>
                                <td>{Order.waiter} </td>
                                
                                <td>
                                <div className="btn-group" role="group">
                                <Link to={"/orders/retrieve/" + Order.id} className="btn btn-warning">
                                    <FaEye /> Ver
                                  </Link>                                  
                                  <Link to={"/orders/update/" + Order.id} className="btn btn-primary">
                                      <FaPen /> Editar
                                  </Link>

                                  <button className="btn btn-danger" onClick={() => removeOrder(Order.id!)}>
                                    <FaTrash /> Eliminar
                                  </button>

                                  
                                </div>
                                    
                                </td>
                            </tr>                        
                        ))}
                    </tbody>
                </table>

                <ReactPaginate
                  className="pagination"
                  breakLabel="..."
                  nextLabel="siguiente >"
                  onPageChange={handlePageClick}
                  pageRangeDisplayed={5}
                  pageCount={pageCount}
                  previousLabel="< anterior"/>

            </div>            
        </div>
     );
}

