
export class ComputerComponent{
    category:string;
    name:string;
    brand:string;
    productLine:string;
    numOfCores:string;
    processorClockSpeed:string;
    graphicClockSpeed:string;
    size:string;
    dimension:string;
    intrfce:string;
    colour:string;
    resolution:string;
    price:number;
    quantity:number;
    uuid:string;
    numInBasket:number;
    constructor(
        category:string,
        name:string,
        brand:string,
        productLine:string,
        numOfCores:string,
        processorClockSpeed:string,
        graphicClockSpeed:string,
        size:string,
        dimension:string,
        intrfce:string,
        colour:string,
        resolution:string,
        price:number,
        quantity:number,
        uuid:string
        ){
this.category = category;
this.name = name;
this.brand = brand;
this.productLine = productLine;
this.numOfCores = numOfCores;
this.processorClockSpeed = processorClockSpeed;
this.graphicClockSpeed = graphicClockSpeed;
this.size = size;
this.dimension = dimension;
this.intrfce = intrfce;
this.colour=colour;
this.resolution = resolution;
this.price = price;
this.quantity=quantity;
this.uuid = uuid;
this.numInBasket=0;
    }

    public getUuid():string{
        return this.uuid;
    }
    public getNumInBasket():number{
        return this.numInBasket;
    }
}