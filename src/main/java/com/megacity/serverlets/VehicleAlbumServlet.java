package com.megacity.serverlets;

import com.megacity.models.VehicleAlbum;
import com.megacity.services.VehicleAlbumService;
import com.megacity.services.impl.VehicleAlbumServiceImpl;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/vehicle")
public class VehicleAlbumServlet extends HttpServlet {
	
}