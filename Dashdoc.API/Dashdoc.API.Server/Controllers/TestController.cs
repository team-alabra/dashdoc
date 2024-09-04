using Dashdoc.API.Domain.Abstract;
using Dashdoc.API.Domain.Enums;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.OpenApi.Extensions;

namespace Dashdoc.API.Server.Controllers;

[ApiController]
[Route("/api/test")]
public class TestController: ControllerBase
{
    private readonly IConfiguration _config;

    public TestController(IConfiguration config)
    {
        _config = config;
    }

    // TODO - replace with real Provider routes
    [HttpGet("all")]
    public ActionResult Get()
    {
        try
        {
            return Ok();
        }
        catch (Exception ex)
        {
            Console.WriteLine(ex);
            return Problem("Problem retrieving Data");
        }
    }
}