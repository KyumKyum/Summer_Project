import express, {Express, Request, Response} from 'express';
const PORT = 5001;
const startService = async() => {
  try{
    const microService:Express = express();
    microService.set('trust proxy',1);

    microService.get('/', (req:Request, res:Response) => {
      res.send("Test Complete!");
    })
    
    microService.listen(PORT, () => {
      console.log(`💫💫💫 Micro Service is running on http://localhost:${PORT} 💫💫💫`);
    })
  }catch(e){
    console.error(e);
  }
};

startService();