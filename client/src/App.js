import React, { useState } from 'react';
import { Box, Button, Container, Paper, Typography } from '@material-ui/core';
import { makeStyles } from '@material-ui/core/styles';
import CloudUploadIcon from '@material-ui/icons/CloudUpload';
import AllSiteInfoTable from './components/allSiteInfoTable/AllSiteInfoTabje';
import './App.css';
import { Route, Switch } from 'react-router';
import { BrowserRouter as Router} from "react-router-dom";
import DetailSiteInfo from './components/detailSiteInfo/DetailSiteInfo';
import TextField from '@material-ui/core/TextField';
import { startParseSite } from './api/parserSiteApi';

const useStyles = makeStyles((theme) => ({
  button: {
    margin: theme.spacing(1),
  },
  header: {
    display: "flex",
    justifyContent: "space-between",
    alignItems: "center",
    padding: '15px'
  },
  headerSearch: {
    display: "flex",
    justifyContent: "space-between",
    alignItems: "center",
  }
}));

function App() {
  const classes = useStyles();
  const [url, setUrl] = useState("");

  const handleUrlInput = (event) => {
    setUrl(event.target.value);
  }

  const handleStartParse = (event) => {
    event.preventDefault();
    startParseSite({url});
  }
  
  return (
    <Router>
      
      <Switch>

        <Route exact path="/"> {/* Отображаем короткую инфу о всех сайтах */}
          <Container>

            <Box mb={3}>
              <Paper className={classes.header}>

                <Typography variant="h5">
                  Website parser
            </Typography>

            <div className={classes.headerSearch}>
              <TextField onChange={handleUrlInput} value={url} id="outlined-basic" label="url" size="small" variant="outlined" />

              <Button
                    variant="contained"
                    onClick={handleStartParse}
                    color="default"
                    className={classes.button}
                    startIcon={<CloudUploadIcon />}
                  >
                    start
              </Button>
            </div>

              </Paper>
            </Box>

            <AllSiteInfoTable></AllSiteInfoTable>
          </Container>
        </Route> {/* ~Отображаем короткую инфу о всех сайтах */}

        <Route exact path="/site/:id"> {/* Отображаем подробную инфу о сайте */}
          <DetailSiteInfo></DetailSiteInfo>
          
        </Route> {/* ~Отображаем подробную инфу о сайте */}
     
     </Switch>

    </Router>

  );
}

export default App;
