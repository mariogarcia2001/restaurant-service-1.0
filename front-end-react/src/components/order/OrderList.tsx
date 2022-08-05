import { FaPen, FaEye, FaTrash, FaPlus } from "react-icons/fa";
import React, { useState, useEffect } from 'react'
import { Link } from 'react-router-dom';
import IOrderModel from '../../models/Order';
import OrderService from '../../services/OrderServices';
import Swal from "sweetalert2";
import ReactPaginate from "react-paginate";
import Dropdown from 'react-dropdown'

export const OrderList = () => {

    //Hook: Define un atributo y la función que lo va a actualizar
    const [orders, setOrders] = useState<Array<IOrderModel>>([]);
    const [itemsCount, setItemsCount] = useState<number>(0);
    const [pageCount, setPageCount] = useState<number>(0);
    const [itemsPerPage, setItemsPerPage] = useState<string>('5');
    const [numberPage, setNumberPage] = useState<number>(0);
    
    //Hook para llamar a la Web API
    useEffect(() => {
      getItems();      
    });

    useEffect(() => {
      listOrders();          
    });   

    const handlePageClick = (event: any) => {        
      setNumberPage(event.selected);                         
    };

    const handleItemPerPageClick = (event : any) => {
      setItemsPerPage(event.value);
    }

    //Función que llama al Service para listar los datos desde la Web API
    const listOrders = () => {
       OrderService.list(numberPage, itemsPerPage)
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
        var numberPerPage = parseInt(itemsPerPage);
        var itemsCount = response;
        setItemsCount(itemsCount);
        setPageCount(Math.ceil(itemsCount/ numberPerPage));
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
                  setNumberPage(0);
                  console.log(response.data);
                })
                .catch((e: Error) => {
                  console.log(e);
                });      

            }
          });        
     };
   
     const options = ["5", "10", "15" ];

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
                            <th>
                            <Dropdown 
                              className="dropdown"
                              menuClassName="dropdown-menu dropdown-item"                              
                              placeholderClassName="btn btn-secondary dropdown-toggle"
                              options={options} 
                              onChange={handleItemPerPageClick}
                              value={itemsPerPage} />
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
                                
                                <td colSpan={2}>
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

                <div className="container">
                <ReactPaginate
                  activeClassName="page-item active"                
                  pageLinkClassName="page-link"
                  containerClassName="pagination"
                  previousLinkClassName="page-link"
                  nextLinkClassName="page-link"
                  previousClassName="page-item"
                  nextClassName="page-item"
                  breakLabel="..."
                  nextLabel=">>"
                  pageClassName="page-item"
                  onPageChange={handlePageClick}                  
                  pageCount={pageCount}
                  previousLabel="<<"/>
                  </div>

            </div>            
        </div>
     );
}

