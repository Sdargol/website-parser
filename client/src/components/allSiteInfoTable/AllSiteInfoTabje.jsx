import React, {useState, useEffect} from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import IconButton from '@material-ui/core/IconButton';
import DeleteIcon from '@material-ui/icons/Delete';
import { deleteSite, getAllSites } from '../../api/parserSiteApi';
import { Link as RouterLink } from 'react-router-dom';
import Link from '@material-ui/core/Link';

const useStyles = makeStyles({
  table: {
    minWidth: 650,
  },
});

export default function AllSiteInfoTable() {
  const classes = useStyles();
  const [sitesInfo, setSitesInfo] = useState([]);

  useEffect(() => {
      getAllSites(setSitesInfo);  
  }, []);

  return (
    <TableContainer component={Paper}>
      <Table className={classes.table} size="small" aria-label="a dense table">
        
        <TableHead style = {{backgroundColor: "#eee"}}>
          <TableRow>
            <TableCell>id</TableCell>
            <TableCell>url сайта</TableCell>
            <TableCell align="right">статус</TableCell>
            <TableCell align="right">удалить</TableCell>
          </TableRow>
        </TableHead>
        
        <TableBody>
          {sitesInfo.map((s) => (
            <TableRow key={s.id}>
              <TableCell >{s.id}</TableCell>
              
              <TableCell >
                <Link component={RouterLink} to={`/site/${s.id}`}>
                {s.name}
                </Link>
              </TableCell>
              
              <TableCell align="right">{s.status}</TableCell>
              
              <TableCell align="right">
                <IconButton aria-label="delete" onClick = {(e) => {
                  e.preventDefault();
                  deleteSite(s.id);

                }}>
                  <DeleteIcon />
                </IconButton>
              </TableCell>
            
            </TableRow>
          ))}
        </TableBody>
      
      </Table>
    </TableContainer>
  );
}