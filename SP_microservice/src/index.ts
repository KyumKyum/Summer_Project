import express, {Express, Request, Response} from 'express';
import {genReqRouter} from './router/routes';
import bodyParser from 'body-parser';
import { authRedisClient } from './database/redis/authClient';
import {DBDataSource} from './database/postgresql/datasource'

const PORT = 5001;

const setUp = async() => {
   //* Auth
   authRedisClient.connect();

   //* DB Connection.
   DBDataSource.initialize()
     .then(() => {
       console.log("👍👍 DB Connected 👍👍");
     })
     .catch(e => {
       console.error("DB Connection Failed! ", e)
       throw e;
     })
}

const startService = async() => {
  try{
    const microService:Express = express();
    microService.set('trust proxy',1);
    microService.use(bodyParser.json());

    microService.get('/', (req:Request, res:Response) => {
      res.send("Test Complete!");
    })

    microService.use('/generate', genReqRouter);
    
    setUp()
      .then(() => {
        microService.listen(PORT, () => {
          console.log(`💫💫💫 Micro Service is running on http://localhost:${PORT} 💫💫💫`);
        });
      })
      .catch(e => {
        console.error("🚫🚫🚫APP CRASHED!!! Something gone wrong...🚫🚫🚫", e);
      })
    
  }catch(e){
    console.error(e);
  }
};

startService();