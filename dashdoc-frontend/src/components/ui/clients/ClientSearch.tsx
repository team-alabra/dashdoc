import React from "react";
import Button from "@components/shared/buttons/Button";
import Paper from "@mui/material/Paper";
import InputBase from "@mui/material/InputBase";
import IconButton from "@mui/material/IconButton";
import SearchIcon from "@mui/icons-material/Search";

export const ClientSearch: React.FC = () => {
  return (
    <>
      <Paper
        component="form"
        sx={{
          p: "2px 4px",
          display: "flex",
          alignItems: "center",
          width: 400,
          marginBottom: "1rem",
          marginTop: "1rem",
          border: "1px solid lightgrey",
        }}
      >
        <InputBase
          sx={{ ml: 1, flex: 1, fontSize: "13px" }}
          placeholder="Search clients..."
          inputProps={{ "aria-label": "search client list" }}
        />
        <IconButton type="button" sx={{ p: "1px" }} aria-label="search">
          <SearchIcon fontSize="large" />
        </IconButton>
      </Paper>
      {/* <Button variant="contained" color="primary" size="large">
        <span>Search</span>
      </Button> */}
    </>
  );
};
