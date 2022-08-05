import { ChangeEvent, useEffect, useState } from "react";
import { FaArrowLeft, FaSave } from "react-icons/fa";
import { Link, useNavigate, useParams } from "react-router-dom";
import IOrderModel from "../../models/Order";
import IRecipeModel from "../../models/Recipe";
import OrderService from "../../services/OrderServices";
import RecipeService from "../../services/RecipeService";

export const RecipeForm = () => {

  const { id, idOrder }= useParams();
  let navigate = useNavigate();


//Model vacío
const initialRecipeModel : IRecipeModel = {
    id: null,
    name: "",
    ingredients: "",
    prepare: "",
    observations: "",
    order : null    
};

//Hooks para gestionar el modelo
const [recipe, setRecipe] = useState<IRecipeModel>(initialRecipeModel);
const [order, setOrder] = useState<IOrderModel>();

//Escucha los cambios en cada control Input y los asigna a los valores del Modelo
const handleInputChange = (event: ChangeEvent<HTMLInputElement>) => {
    const { name, value } = event.target;
    setRecipe({ ...recipe, [name]: value });
};

useEffect(() => {
  if (idOrder)
  getOrder(idOrder);
});

const getOrder = (id: any) => {
  OrderService.retrieve(id)
    .then((response: any) => {
      setOrder(response.data); //Víncula el resultado del servicio con la función del Hook useState
      recipe.order = order!;
      console.log(response.data);
    })
    .catch((e: Error) => {
      console.log(e);
    });
};

const saveRecipe = () => {        
    if(recipe.id !== null)
    {
      /*RecipeService.update(order)
      .then((response: any) => {
        navigate("/orders");
        console.log(response.data);
      })
      .catch((e: Error) => {
        console.log(e);
      });*/
    }
    else
    {
        RecipeService.create(recipe)
        .then((response: any) => {    
          navigate("/orders");
          console.log(response.data);
        })
        .catch((e: Error) => {
          console.log(e);
        });
    }
  };

return ( //JSX
	<div className="submit-form">				
		<div>
				{ recipe.id !== null ? (<h1>Actualizado la receta {recipe.name}</h1>) : (<h1>Registro de nueva receta</h1>) }            
        { order ? (<h3>{order.description} </h3>) : (<h3>N/A</h3>) }
						<div className="form-group">
						<label htmlFor="title">Receta</label>
            <input
              type="text"
							placeholder="Ingrese el nombre de la Receta"
              className="form-control"
              id="name"
              required
              value={recipe.name}
              onChange={handleInputChange}
              name="name"
            />
						<label htmlFor="ingredients">Ingredientes</label>
            <input						
              type="text"
              className="form-control"
							placeholder="Ingrese los ingredientes de la receta"
              id="ingredients"
              required
              value={recipe.ingredients}
              onChange={handleInputChange}
              name="ingredients"
            />

<label htmlFor="prepare">Preparación</label>
            <input						
              type="text"
              className="form-control"
							placeholder="Ingrese la preparación de la receta"
              id="prepare"
              required
              value={recipe.prepare}
              onChange={handleInputChange}
              name="prepare"
            />

<label htmlFor="observations">Observaciones</label>
            <input						
              type="text"
              className="form-control"
							placeholder="Ingrese alguna observacion"
              id="observations"
              required
              value={recipe.observations}
              onChange={handleInputChange}
              name="observations"
            />
			
						<br />
							<div className="btn-group" role="group">								
                <Link to={"/orders"} className="btn btn-primary">
                    <FaArrowLeft /> Volver
                </Link>
								<button type="button" onClick={saveRecipe} className="btn btn-success">
                  <FaSave />Guardar
                </button>
							</div>
						</div>
					</div>				
			</div>        
    );
}