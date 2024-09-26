using System.Security.Claims;
using Dashdoc.API.Domain.Models;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace Dashdoc.API.Server.Controllers;

[ApiController]
[Route("/api/test")]
[Authorize]
public class TestController: ControllerBase
{
    public TestController() { }

    // TODO - replace with real Provider routes
    [HttpGet]
    public ActionResult GetV1()
    {
        try
        {
            return Ok("Success");
        }
        catch (Exception ex)
        {
            Console.WriteLine(ex);
            return Problem("Problem retrieving Data");
        }
    }
    
    [HttpGet("admin")]
    [Authorize(Roles = UserRoles.Admin)]
    public ActionResult GetV2([FromHeader(Name = "userEmail")] string? userEmail)
    {
        try
        {
            return Ok($"UserId is {userEmail}");
        }
        catch (Exception ex)
        {
            Console.WriteLine(ex);
            return Problem("Problem retrieving Data");
        }
    }
}