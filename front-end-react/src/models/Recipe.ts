import IOrderModel from "./Order";
export default interface IRecipeModel {
    id?: number | null,
    name : string,
    ingredients : string,
    prepare : string,
    observations : string,
    order: IOrderModel | null
}