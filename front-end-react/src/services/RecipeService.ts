import Swal from "sweetalert2";
import http from "../http-common";
import IRecipeModel from "../models/Recipe";

const create = async (data: IRecipeModel) => {    
  try {
    const url : string = "/orders/" + data.order!.id + "/recipes";
    const response = await http.post<IRecipeModel>(url, data);
    if(response.status === 201){
      Swal.fire({
        icon: 'success',
        title: 'Correcto',
        text: 'La receta ha sido creado correctamente',
        confirmButtonText: 'Aceptar'    

      });
    }
    console.log(response);
  } catch (err) {
    console.log(err);
    Swal.fire({
      icon: 'error',
      title: '¡Error!',
      text: 'Network Error',
      confirmButtonText: 'Aceptar'    
    });
  }
};
/*
const retrieve = async (id: number) => {
    return http.get<IOrderData>(`/orders/${id}`);
};

const update = async (data: IOrderData) => {
  try {    
    const response = await http.put<IOrderData>(`/orders/${data.id}`, data);
    if(response.status === 200){
      Swal.fire({
        icon: 'success',
        title: 'Correcto',
        text: 'La orden ha sido actualizada',
        confirmButtonText: 'Aceptar'    
      });
    }

  } catch (error) {
    Swal.fire({
      icon: 'error',
      title: '¡Error!',
      text: 'Network Error',
      confirmButtonText: 'Aceptar'    
    });
  }
    
};

const remove = async (id: number) => {
    try {
      const response = await  http.delete<string>(`/orders/${id}`);
      if(response.status === 200){
        Swal.fire({
          icon: 'success',
          title: 'Correcto',
          text: 'La orden ha sido eliminada',
          confirmButtonText: 'Aceptar'    
        });
      }
    } catch (error) {
      Swal.fire({
      icon: 'error',
      title: '¡Error!',
      text: 'Network Error',
      confirmButtonText: 'Aceptar'    
    });
    }

};


const list = (page: number, size: string, sort? : String) => {
  const urlRequest : string = "/orders/" + page + "/" + size ;
  console.log(urlRequest);
  return http.get<Array<IOrderData>>(urlRequest);
};

const count = async () =>  {  
  const response = await http.get<number>("/orders/count");
  return response.data;
};
*/
const RecipeService = {
  create,
  //retrieve,
  //update,
  //remove,
  //list,
  //count

};
export default RecipeService;