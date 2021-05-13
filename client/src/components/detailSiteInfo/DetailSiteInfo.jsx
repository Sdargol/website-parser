import React, { useState, useEffect } from 'react';
import { Box, Button, Container, Paper, Typography } from '@material-ui/core';
import { makeStyles } from '@material-ui/core/styles';
import { useHistory, useParams } from 'react-router';
import { getSiteInfo } from '../../api/parserSiteApi';
import { FixedSizeList } from 'react-window';
import ArrowBackIcon from '@material-ui/icons/ArrowBack';
import './detailSiteInfoStyle.css';

const useStyles = makeStyles((theme) => ({
    button: {
        margin: theme.spacing(1),
    },
    c: {
        display: "flex",
        justifyContent: "space-around"
    },
    header: {
        display: "flex",
        justifyContent: "space-between",
        alignItems: "center"
    }
}));

function renderRow(props) {
    const { index, style, data } = props;

    return (
        <div className="rowWordInfo" key={index} style={style}>
            <Typography variant="h6">{data[index].word}</Typography>
            <Typography variant="h6">{data[index].count}</Typography>
        </div>
    );
}

export default function DetailSiteInfo() {
    const classes = useStyles();
    
    const { id } = useParams();
    const history = useHistory();

    const [siteInfo, setSiteInfo] = useState({});

    useEffect(() => {
        getSiteInfo(id, setSiteInfo);
    }, [id]);

    return (
        <Container >
            <Box mb={3}>
                <Paper style={{ padding: '15px' }} className={classes.header}>
                    <Typography variant="h5">
                        Сайт: {siteInfo.hasOwnProperty('statistics') ? siteInfo.name : id}
                    </Typography>

                    {siteInfo.hasOwnProperty('statistics') ? <Typography variant="body1">Найдено слов: {siteInfo.statistics.countWord}</Typography> : false}
                    
                    <Button
                        variant="contained"
                        color="default"
                        className={classes.button}
                        startIcon={<ArrowBackIcon />}
                        onClick={(e)=>{history.push("/")}}
                    >
                        назад
                    </Button>
                </Paper>
            </Box>

            <div className={classes.c}>
                {siteInfo.hasOwnProperty('statistics') ? <FixedSizeList height={400} width={300} itemSize={46} itemCount={siteInfo.statistics.countWord} itemData={siteInfo.statistics.stats}>
                    {renderRow}
                </FixedSizeList> : "Грузим"}
            </div>
        </Container>
    );
}

